(ns plik.parser.core
  (:require [plik.parser.sniffer]
            [plik.parser.reader]
            [plik.parser.writer]))

(defn run
  [input]
  (plik.parser.writer/write-json-rows
   (plik.parser.reader/load-data input
                                 (plik.parser.sniffer/infer-deliminator input))))
