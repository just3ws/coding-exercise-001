(ns delimited-file-reader.sniffer
  (:require [clojure.java.io :as io]
            [clojure.pprint :refer :all]))


(defn open-file [path] (io/file (io/resource path)))

(defn peek-file [file]
  (with-open [rdr (io/reader file)]
    (nth (line-seq rdr) 0)))

(defn pipe-delimited? [file]
  (let [str (peek-file file)]
    (not (nil? (re-find #"\s+\|\s+" str)))))

(defn comma-delimited? [file]
  (let [str (peek-file file)]
    (not (nil? (re-find #",\s+" str)))))

(defn space-delimited? [file]
  (let [str (peek-file file)]
    (if (and (not (comma-delimited? file)) (not (pipe-delimited? file)))
      (not (nil? (re-find #"\s+" str)))
      false)))
