(ns plik.reader
  (:require [clojure.string :as s]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [semantic-csv.core :as sc]
            [camel-snake-kebab.core :refer :all]
            [plik.date])
  (:import (java.nio.file Paths)))

(defn open-file
  [path]
  (io/file (io/resource path)))

(defn file-exists?
  [path]
  (.exists (io/as-file path)))

(defn append-to-base-path
  [base-path to-join]
  (str (Paths/get base-path (into-array to-join))))

(defn directory-exists?
  [path]
  (.isDirectory (io/file path)))

(defn transform-header
  [header]
  (->> header
       s/trim
       ->snake_case_keyword))

(defn cast-date-of-birth
  [dob]
  (when (plik.date/date? dob) (plik.date/read-date dob)))

(defn peek-file
  [file]
  (with-open [in-file (io/reader file)]
    (nth (line-seq in-file) 0)))

(defn load-data
  [file delimiter]
  (with-open [in-file (io/reader file)]
    (->>
     (csv/read-csv in-file :separator delimiter)
     (sc/remove-comments)
     (sc/mappify {:transform-header transform-header :keyify true})
     (sc/cast-with #(->> % str s/trim))
     (sc/cast-with {:date_of_birth cast-date-of-birth})
     doall)))
