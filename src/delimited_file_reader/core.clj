(ns delimited-file-reader.core
  (:require [delimited-file-reader.records])
  (:import [delimited_file_reader.records Person])
  (:gen-class))

(defn -main
  "Command-line entry point."
  [path]
  (println path))

