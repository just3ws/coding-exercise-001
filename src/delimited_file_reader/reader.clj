(ns delimited-file-reader.reader
  (:require [clj-time.format :as f]))

(def date-format (f/formatter "M/d/yyyy"))
(defn read-date [str] (f/parse date-format str))
(defn write-date [date] (f/unparse date-format date))
(defn date? [v]
  (if (instance? org.joda.time.DateTime v)
    true
    (instance? org.joda.time.DateTime (try (read-date v)
                                           (catch java.lang.IllegalArgumentException e)))))
