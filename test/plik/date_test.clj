(ns plik.date-test
  (:require [clojure.test :refer :all]
            [plik.date :as subject]))

(deftest date-parsing-test
  (is (= "12/19/1975" (subject/write-date (subject/read-date "12/19/1975"))))
  (is (= "7/6/1975" (subject/write-date (subject/read-date "7/6/1975"))))
  (is (false? (subject/date? "hello")))
  (is (true? (subject/date? "7/6/1975"))))
