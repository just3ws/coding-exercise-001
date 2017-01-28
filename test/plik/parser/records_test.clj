(ns plik.parser.records-test
  (:require [clojure.test :refer :all]
            [plik.parser.date]
            [plik.parser.records :refer :all])
  (:import [plik.parser.records Person]
           (org.joda.time DateTime)))

;; Directly initialize Person defrecord
(testing "Directly generate a Person record"
  (let [favorite-color "green"
        date-of-birth "7/6/1975"
        gender "F"
        first-name "Aneta"
        last-name "Hall"]

    (def aneta (Person. last-name first-name gender date-of-birth favorite-color))

    (testing "with valid attributes"
      (is (identical? (:last_name aneta) last-name))
      (is (identical? (:first_name aneta) first-name))
      (is (identical? (:gender aneta) gender))
      (is (identical? (:date_of_birth aneta) date-of-birth))
      (is (identical? (:favorite_color aneta) favorite-color)))))

;; Initialize Person record using constructor function
(testing "Construct a Person record"
  (let [favorite-color "blue"
        date-of-birth "12/19/1975"
        gender "M"
        first-name "Mike"
        last-name "Hall"]

    (def mike (plik.parser.records/make-person {:favorite_color favorite-color
                                         :date_of_birth  date-of-birth
                                         :gender         gender
                                         :first_name     first-name
                                         :last_name      last-name}))

    (testing "with valid attributes"
      (is (identical? (:last_name mike) last-name))
      (is (identical? (:first_name mike) first-name))
      (is (identical? (:gender mike) gender))
      (is (identical? (:favorite_color mike) favorite-color))
      (is (instance? DateTime (:date_of_birth mike)))
      (is (plik.parser.date/date? (:date_of_birth mike))))))
