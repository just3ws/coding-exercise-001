(ns plik.date
  (:require [clj-time.format :as f]
            [clojure.string :as s]))

(def date-format (f/formatter "M/d/yyyy"))

(defn read-date
  [str]
  (f/parse date-format (s/trim str)))

(defn write-date
  [date]
  (f/unparse date-format date))

(defn date?
  [v]
  (if (instance? org.joda.time.DateTime v)
    true
    (instance? org.joda.time.DateTime (try (read-date v)
                                           (catch java.lang.IllegalArgumentException e)))))
