(ns plik.sniffer
  (:require [clojure.java.io :as io]))

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

