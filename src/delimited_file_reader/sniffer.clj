(ns delimited-file-reader.sniffer
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [semantic-csv.core :as sc]
            [delimited-file-reader.date :as d]
            [camel-snake-kebab.core :refer :all]
            [clojure.string :as s]))

(defn open-file [path] (io/file (io/resource path)))

(defn peek-file [file]
  (with-open [in-file (io/reader file)]
    (nth (line-seq in-file) 0)))

(defn pipe-delimited? [file]
  (let [str (peek-file file)]
    (not (nil? (re-find #"\|" str)))))

(defn comma-delimited? [file]
  (let [str (peek-file file)]
    (not (nil? (re-find #"," str)))))

(defn space-delimited? [file]
  (let [str (peek-file file)]
    (if (and (not (comma-delimited? file)) (not (pipe-delimited? file)))
      (not (nil? (re-find #"\s+" str)))
      false)))

(defn infer-deliminator [file]
  (cond
    (comma-delimited? file) \,
    (pipe-delimited? file) \|
    (space-delimited? file) \space))

(defn transform-header [header]
  (->> header
       s/trim
       ->snake_case_keyword))

(defn cast-date-of-birth [dob]
  (if (d/date? dob)
    (d/read-date dob)
    nil))

(defn load-data [file]
  (let [deliminator (infer-deliminator file)]
    (with-open [in-file (io/reader file)]
      (->>
        (csv/read-csv in-file :separator deliminator)
        (sc/remove-comments)
        (sc/mappify { :transform-header transform-header :keyify true })
        (sc/cast-with #(->> % str s/trim))
        (sc/cast-with { :date_of_birth cast-date-of-birth })
        doall))))
