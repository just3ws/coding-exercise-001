(ns plik.writer
  (:require [plik.transformer]))

(defn write-json-rows
  [output data]
  (doall (map (fn [json-row] (spit output (str json-row "\n") :append true))
              (plik.transformer/transform-json-sequence data))))
