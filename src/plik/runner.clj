(ns plik.runner
  (:require [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts]]
            [plik.reader]
            [plik.writer]
            [plik.sniffer])
  (:gen-class))

(def cli-options
  [["-i" "--input INPUT" "Required. Fully-qualified path to input file"
    :parse-fn #(-> % str string/trim)
    :validate [#(plik.reader/file-exists? %) "The input file must exist"]
    :required true]
   ["-o" "--output OUTPUT" "Required. Fully-qualified path to output directory"
    :parse-fn #(-> % str string/trim)
    :validate [#(plik.reader/directory-exists? %) "The output directory must exist. File will be created or appended."]
    :required true]
   ["-h" "--help" "Print this message" :default false]])

(defn usage
  [options-summary]
  (string/join
    \newline
    ["plik: Read in delimited files, optionally sort, then write as JSON"
     ""
     "Usage: plik [options]"
     ""
     "Options:"
     options-summary]))

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
      (not= (zero? (count arguments))) (exit 1 (usage summary))
      (zero? (count (str (:input options)))) (exit 1 (usage summary))
      (zero? (count (str (:output options)))) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))
    ;; Execute program with options
    (let [input (:input options)
          output (plik.reader/append-to-base-path (:output options) ["plik-data.json"])]
      (let [data (plik.reader/load-data input (plik.sniffer/infer-deliminator input))]
        (plik.writer/write-json-rows output data)))))
