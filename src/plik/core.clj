(ns plik.core
  (:require [plik.sniffer]
            [plik.reader]
            [plik.writer]))

(defn run
  [input]
  (plik.writer/write-json-rows
   (plik.reader/load-data input
                          (plik.sniffer/infer-deliminator input))))
