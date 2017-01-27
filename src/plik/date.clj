(ns plik.date
  (:require [clj-time.format :as f]
            [clojure.string :as s])
  (:import (org.joda.time DateTime)))

(def date-format (f/formatter "M/d/yyyy"))

(defn read-date
  [str]
  (f/parse date-format (s/trim str)))

(defn write-date
  [date]
  (f/unparse date-format date))

(defn date?
  [v]
  (if (instance? DateTime v)
    true
    (instance? DateTime (try (read-date v)
                             (catch IllegalArgumentException e)))))
