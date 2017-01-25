(ns delimited-file-reader.sniffer
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

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

(defn load-data [file]
  (let [deliminator (infer-deliminator file)]
  (with-open [in-file (io/reader file)]
    (doall
      (csv/read-csv in-file :separator deliminator)))))
