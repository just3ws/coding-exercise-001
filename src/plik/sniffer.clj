(ns plik.sniffer
  (:require [plik.reader]))

(defn pipe-delimited?
  [file]
  (let [str (plik.reader/peek-file file)]
    (not (nil? (re-find #"\|" str)))))

(defn comma-delimited?
  [file]
  (let [str (plik.reader/peek-file file)]
    (not (nil? (re-find #"," str)))))

(defn space-delimited?
  [file]
  (let [str (plik.reader/peek-file file)]
    (if (and (not (comma-delimited? file)) (not (pipe-delimited? file)))
      (not (nil? (re-find #"\s+" str)))
      false)))

(defn infer-deliminator
  [file]
  (cond
    (comma-delimited? file) \,
    (pipe-delimited? file) \|
    (space-delimited? file) \space))

