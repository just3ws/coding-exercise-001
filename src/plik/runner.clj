(ns plik.runner
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.pprint :refer :all]
            [plik.reader :as r]
            [plik.writer :as w]
            [plik.sniffer :as sniffer]
            [clojure.string :as s])
  (:gen-class))

(def cli-options
  [["-i" "--input INPUT" "Fully-qualified path to the input file"
    :parse-fn #(-> % str s/trim)
    :validate [#(r/file-exists? %) "The input file must exist"]]
   ["-o" "--output OUTPUT" "Fully-qualified path to the output file"
    :parse-fn #(-> % str s/trim)]])

(defn -main
  [& args]
  (let [{:keys [options arguments]} (parse-opts args cli-options)]
    (let [input (get options :input)
          output (get options :output)]
      (let [data (r/load-data input (sniffer/infer-deliminator input))]
        (w/write-json-rows output data)))))
