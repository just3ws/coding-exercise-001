(ns plik.reader-test
  (:require [clojure.test :refer :all]
            [clj-time.core :as t]
            [plik.reader :as subject])
  (:import (org.joda.time DateTime)))

(def pipe-file (subject/open-file "test/pipe.txt"))
(def comma-file (subject/open-file "test/comma.txt"))
(def space-file (subject/open-file "test/space.txt"))

(testing "Read in pipe file and converting to data"
  (let [data (subject/load-data pipe-file \|)]
    (is (= 10 (count data)))
    (testing "verify the first record is as expected"
      (let [datum (first data)]
        (is (false? (nil? datum)))
        (is (= "Green" (:last_name datum)))
        (is (= "Abbey" (:first_name datum)))
        (is (= "M" (:gender datum)))
        (is (= "Orchid" (:favorite_color datum)))
        (is (instance? DateTime (:date_of_birth datum)))
        (is (= (t/date-time 2021 10 10) (:date_of_birth datum)))))
    (testing "verify the last record is as expected"
      (let [datum (last data)]
        (is (false? (nil? datum)))
        (is (= "Lesch" (:last_name datum)))
        (is (= "Emerald" (:first_name datum)))
        (println (:gender datum))
        (is (= "M" (:gender datum)))
        (is (= "Tan" (:favorite_color datum)))
        (is (instance? DateTime (:date_of_birth datum)))
        (is (= (t/date-time 2021 8 19) (:date_of_birth datum)))))))

(testing "Read in comma file and converting to data"
  (let [data (subject/load-data comma-file \,)]
    (is (= 10 (count data)))
    (testing "verify the first record is as expected"
      (let [datum (first data)]
        (is (false? (nil? datum)))
        (is (= "Green" (:last_name datum)))
        (is (= "Abbey" (:first_name datum)))
        (is (= "M" (:gender datum)))
        (is (= "Orchid" (:favorite_color datum)))
        (is (instance? DateTime (:date_of_birth datum)))
        (is (= (t/date-time 2021 10 10) (:date_of_birth datum)))))
    (testing "verify the last record is as expected"
      (let [datum (last data)]
        (is (false? (nil? datum)))
        (is (= "Lesch" (:last_name datum)))
        (is (= "Emerald" (:first_name datum)))
        (is (= "M" (:gender datum)))
        (is (= "Tan" (:favorite_color datum)))
        (is (instance? DateTime (:date_of_birth datum)))
        (is (= (t/date-time 2021 8 19) (:date_of_birth datum)))))))

(testing "Read in space file and converting to data"
  (let [data (subject/load-data space-file \space)]
    (is (= 10 (count data)))
    (testing "verify the first record is as expected"
      (let [datum (first data)]
        (is (false? (nil? datum)))
        (is (= "Green" (:last_name datum)))
        (is (= "Abbey" (:first_name datum)))
        (is (= "M" (:gender datum)))
        (is (= "Orchid" (:favorite_color datum)))
        (is (instance? DateTime (:date_of_birth datum)))
        (is (= (t/date-time 2021 10 10) (:date_of_birth datum)))))
    (testing "verify the last record is as expected"
      (let [datum (last data)]
        (is (false? (nil? datum)))
        (is (= "Lesch" (:last_name datum)))
        (is (= "Emerald" (:first_name datum)))
        (is (= "M" (:gender datum)))
        (is (= "Tan" (:favorite_color datum)))
        (is (instance? DateTime (:date_of_birth datum)))
        (is (= (t/date-time 2021 8 19) (:date_of_birth datum)))))))
