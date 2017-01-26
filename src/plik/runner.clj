(ns plik.runner
  (:require [clojure.tools.cli :refer [parse-opts]]
            [plik.reader :as r]
            [clojure.string :as s])
  (:gen-class))

(def cli-options
  [["-i" "--input INPUT" "Fully-qualified path to the input file"
    :parse-fn #(-> % str s/trim)
    :validate [#(r/file-exists? %) "The input file must exist"]]])

(defn -main
  [& args]
  (let [{:keys [options arguments]} (parse-opts args cli-options)]
    (println options)
    (let [input (get options :input)]
      (println input)
      (println (r/load-data input)))))

;(println (r/file-exists? "/Users/mike/just3ws/coding-exercise-001/CHANGELOG.md"))
;(println (r/file-exists? "/Users/mike/just3ws/coding-exercise-001/NOPE.txt"))
