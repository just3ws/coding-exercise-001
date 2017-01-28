(ns plik.parser.sniffer-test
  (:require [clojure.test :refer :all]
            [plik.parser.reader]
            [plik.parser.sniffer :as subject]))

(def pipe-file (plik.parser.reader/open-file "test/pipe.txt"))
(def comma-file (plik.parser.reader/open-file "test/comma.txt"))
(def space-file (plik.parser.reader/open-file "test/space.txt"))

(deftest pipe-test
  (is (subject/pipe-delimited? pipe-file))
  (is (not (subject/pipe-delimited? comma-file)))
  (is (not (subject/pipe-delimited? space-file))))

(deftest comma-test
  (is (subject/comma-delimited? comma-file))
  (is (not (subject/comma-delimited? pipe-file)))
  (is (not (subject/comma-delimited? space-file))))

(deftest space-test
  (is (subject/space-delimited? space-file))
  (is (not (subject/space-delimited? pipe-file)))
  (is (not (subject/space-delimited? comma-file))))

(deftest infer-deliminator-test
  (is (= \space (subject/infer-deliminator space-file)))
  (is (= \| (subject/infer-deliminator pipe-file)))
  (is (= \, (subject/infer-deliminator comma-file))))

