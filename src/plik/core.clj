(ns plik.core
  (:require [plik.sniffer]
            [plik.reader]
            [plik.writer]))

(defn run
  [input]
  (let [data (plik.reader/load-data input (plik.sniffer/infer-deliminator input))]
    (plik.writer/write-json-rows data)))
