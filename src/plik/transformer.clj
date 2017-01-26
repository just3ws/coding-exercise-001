(ns plik.transformer
  (:require [clojure.string :as s]
            [clojure.data.json :as json]
            [plik.date :as d]
            [clj-time.format :as f]))

(defn as-date-string
  [date]
  (f/unparse d/date-format date))

(defn date-aware-value-writer
  [key value]
  (if (= key :date_of_birth) (as-date-string value) value))

(defn jsonify
  [data]
  (json/write-str data :value-fn date-aware-value-writer))
