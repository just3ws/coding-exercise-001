(ns plik.writer
  (:require [plik.transformer]))

(defn write-json-rows
  [data]
  (doall (map println (plik.transformer/transform-json-sequence data))))
