(ns delimited-file-reader.sniffer-test
  (:require [clojure.test :refer :all]
            [delimited-file-reader.sniffer :refer :all]
            [clojure.java.io :as io]))

(def pipe-file (io/file (io/resource "test/pipe.txt")))
(def comma-file (io/file (io/resource "test/comma.txt")))
(def space-file (io/file (io/resource "test/space.txt")))

