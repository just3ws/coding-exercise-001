(ns plik.runner
	(:require [clojure.tools.cli :refer [parse-opts]]
						[clojure.pprint :refer :all]
						[plik.reader :as r]
						[plik.writer :as w]
						[plik.sniffer :as sniffer]
						[clojure.string :as string])
	(:gen-class))

(def cli-options
	[["-i" "--input INPUT" "Fully-qualified path to input file"
		:parse-fn #(-> % str string/trim)
		:validate [#(r/file-exists? %) "The input file must exist"]
		:required true]
	 ["-o" "--output OUTPUT" "Fully-qualified path to output directory"
		:parse-fn #(-> % str string/trim)
		:validate [#(r/directory-exists? %) "The output directory must exist. File will be created or appended."]
		:required true]
	 ["-h" "--help" "Print this message" :default false]])

(defn usage
	[options-summary]
	(->> ["plik: Read in delimited files, optionally sort, then write as JSON"
				""
				"Usage: plik [options]"
				""
				"Options:"
				options-summary]
			 (string/join \newline)))

(defn error-msg
	[errors]
	(str "The following errors occurred while parsing your command:\n\n"
			 (string/join \newline errors)))

(defn exit
	[status msg]
	(println msg)
	(System/exit status))

(defn -main
	[& args]
	(let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
		;; Handle help and error conditions
		(cond
			(:help options) (exit 0 (usage summary))
			(not= (count arguments) 0) (exit 1 (usage summary))
			errors (exit 1 (error-msg errors)))
		;; Execute program with options
		(let [input (:input options)
					output (r/append-to-base-path (:output options) ["plik-data.json"]) ]
			(let [data (r/load-data input (sniffer/infer-deliminator input))]
				(w/write-json-rows output data)))))
