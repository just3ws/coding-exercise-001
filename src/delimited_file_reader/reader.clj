(ns delimited-file-reader.reader
  (:require [clj-time.format :as f]))

(def date-format (f/formatter "M/d/yyyy"))
(defn read-date [str] (f/parse date-format str))
(defn write-date [date] (f/unparse date-format date))
(defn date? [str] (instance? org.joda.time.DateTime (try (read-date str)
                                                         (catch java.lang.IllegalArgumentException e))))
