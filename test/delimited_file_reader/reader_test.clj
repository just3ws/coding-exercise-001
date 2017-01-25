(ns delimited-file-reader.reader-test
  (:require [clojure.test :refer :all]
            [clojure.pprint :refer :all]
            [clj-time.core :as t]
            [delimited-file-reader.reader :as r]
            ))

(def pipe-file (r/open-file "test/pipe.txt"))
(def comma-file (r/open-file "test/comma.txt"))
(def space-file (r/open-file "test/space.txt"))

(testing "Read in pipe file and converting to data"
  (let [data (r/load-data pipe-file)]
    (is (= 10 (count data)))
    (testing "verify the first record is as expected"
      (let [datum (first data)]
        (is (false? (nil? datum)))
        (is (= "Green" (get datum :last_name )))
        (is (= "Abbey" (get datum :first_name )))
        (is (= "M" (get datum :gender )))
        (is (= "Orchid" (get datum :favorite_color)))
        (is (instance? org.joda.time.DateTime (get datum :date_of_birth)))
        (is (= (t/date-time 2021 10 10) (get datum :date_of_birth)))))
    (testing "verify the last record is as expected"
      (let [datum (last data)]
        (is (false? (nil? datum)))
        (is (= "Lesch" (get datum :last_name )))
        (is (= "Emerald" (get datum :first_name )))
        (is (= "M" (get datum :gender )))
        (is (= "Tan" (get datum :favorite_color)))
        (is (instance? org.joda.time.DateTime (get datum :date_of_birth)))
        (is (= (t/date-time 2021 8 19) (get datum :date_of_birth)))))))

(testing "Read in comma file and converting to data"
  (let [data (r/load-data comma-file)]
    (is (= 10 (count data)))
    (testing "verify the first record is as expected"
      (let [datum (first data)]
        (is (false? (nil? datum)))
        (is (= "Green" (get datum :last_name )))
        (is (= "Abbey" (get datum :first_name )))
        (is (= "M" (get datum :gender )))
        (is (= "Orchid" (get datum :favorite_color)))
        (is (instance? org.joda.time.DateTime (get datum :date_of_birth)))
        (is (= (t/date-time 2021 10 10) (get datum :date_of_birth)))))
    (testing "verify the last record is as expected"
      (let [datum (last data)]
        (is (false? (nil? datum)))
        (is (= "Lesch" (get datum :last_name )))
        (is (= "Emerald" (get datum :first_name )))
        (is (= "M" (get datum :gender )))
        (is (= "Tan" (get datum :favorite_color)))
        (is (instance? org.joda.time.DateTime (get datum :date_of_birth)))
        (is (= (t/date-time 2021 8 19) (get datum :date_of_birth)))))))

(testing "Read in space file and converting to data"
  (let [data (r/load-data space-file)]
    (is (= 10 (count data)))
    (testing "verify the first record is as expected"
      (let [datum (first data)]
        (is (false? (nil? datum)))
        (is (= "Green" (get datum :last_name )))
        (is (= "Abbey" (get datum :first_name )))
        (is (= "M" (get datum :gender )))
        (is (= "Orchid" (get datum :favorite_color)))
        (is (instance? org.joda.time.DateTime (get datum :date_of_birth)))
        (is (= (t/date-time 2021 10 10) (get datum :date_of_birth)))))
    (testing "verify the last record is as expected"
      (let [datum (last data)]
        (is (false? (nil? datum)))
        (is (= "Lesch" (get datum :last_name )))
        (is (= "Emerald" (get datum :first_name )))
        (is (= "M" (get datum :gender )))
        (is (= "Tan" (get datum :favorite_color)))
        (is (instance? org.joda.time.DateTime (get datum :date_of_birth)))
        (is (= (t/date-time 2021 8 19) (get datum :date_of_birth)))))))
