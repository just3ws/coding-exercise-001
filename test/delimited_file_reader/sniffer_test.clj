(ns ^:focus delimited-file-reader.sniffer-test
  (:require [clojure.test :refer :all]
            [delimited-file-reader.sniffer :refer :all]
            [clojure.pprint :refer :all]
            [clojure.java.io :as io]))

(def pipe-file (open-file "test/pipe.txt"))
(def comma-file (open-file "test/comma.txt"))
(def space-file (open-file "test/space.txt"))

(deftest pipe-test
  (is (pipe-delimited? pipe-file))
  (is (not (pipe-delimited? comma-file)))
  (is (not (pipe-delimited? space-file))))

(deftest comma-test
  (is (comma-delimited? comma-file))
  (is (not (comma-delimited? pipe-file)))
  (is (not (comma-delimited? space-file))))

(deftest space-test
  (is (space-delimited? space-file))
  (is (not (space-delimited? pipe-file)))
  (is (not (space-delimited? comma-file))))

(deftest infer-deliminator-test
  (is (= \space (infer-deliminator space-file)))
  (is (= \| (infer-deliminator pipe-file)))
  (is (= \, (infer-deliminator comma-file))))

(pprint (load-data pipe-file))
