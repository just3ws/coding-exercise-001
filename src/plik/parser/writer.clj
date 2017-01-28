(ns plik.parser.writer
  (:require [plik.parser.transformer]))

(defn write-json-rows
  [data]
  (doall (map println (plik.parser.transformer/transform-json-sequence data))))
