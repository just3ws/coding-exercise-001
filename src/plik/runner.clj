(ns plik.runner
	(:require [clojure.tools.cli :refer [parse-opts]]
            [plik.reader :as r]
						[clojure.string :as s])
	(:gen-class))


;(def cli-options
	;[["-i" "--input INPUT" "Input file"
		;:parse-fn #(-> % str s/trim)
		;;:validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]
		;]
	 ;["-v" nil "Verbosity level" :id :verbosity
		;:default 0
		;:assoc-fn (fn [m k _] (update-in m [k] inc))]
	 ;["-h" "--help"]])

(defn -main
	"Command-line entry point."
	[& args]
	(println args))

(println (r/file-exists"/Users/mike/just3ws/coding-exercise-001/CHANGELOG.md"))
(println (r/file-exists "/Users/mike/just3ws/coding-exercise-001/NOPE.txt"))

