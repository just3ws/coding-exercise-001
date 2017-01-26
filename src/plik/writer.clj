(ns plik.writer
  (:require [plik.transformer :as t]))

(defn write-json-rows
  [path data]
  (doall (map #(println (t/jsonify %)) data)))
