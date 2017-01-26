(ns plik.writer
  (:require [plik.transformer :as t]))


(defn write-json-rows
  [output data]
  (doall (map (fn [json-row] (spit output (str json-row "\n") :append true))
       (t/transform-json-sequence data))))
