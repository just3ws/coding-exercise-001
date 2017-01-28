(ns plik.parser.transformer
  (:require [clojure.string :as s]
            [clojure.data.json :as json]
            [clj-time.format :as f]
            [plik.parser.date]))

(defn as-date-string
  [date]
  (f/unparse plik.parser.date/date-format date))

(defn date-aware-value-writer
  [key value]
  (if (= key :date_of_birth) (as-date-string value) value))

(defn jsonify
  [data]
  (json/write-str data :value-fn date-aware-value-writer))

(defn transform-json-sequence
  [data]
  (map jsonify data))
