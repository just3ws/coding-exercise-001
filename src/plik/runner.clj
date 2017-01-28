(ns plik.runner
  (:require [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts]]
            [plik.core])
  (:gen-class))

(def cli-options
  [["-i" "--input INPUT" "Required. Fully-qualified path to input file"
    :parse-fn #(-> % str string/trim)
    :validate [#(plik.reader/file-exists? %) "The input file must exist"]
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
      (not (zero? (count arguments))) (exit 1 (usage summary))
      (zero? (count (str (:input options)))) (exit 1 (usage summary))
      errors (exit 1 (error-msg errors)))
    ;; Execute program with options
    (plik.core/run (:input options))))
