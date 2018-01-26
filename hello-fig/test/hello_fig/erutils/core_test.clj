(ns hello-fig.erutils.core-test
  (:require [clojure.spec.alpha :as s]
            [clojure.test :as tst ]
            [clojure.pprint :as pp]
            [hello-fig.erutils.core :as erm ]))



(tst/deftest basic-erm-structure
(def erm1
  {:entities [:user :manager ]
   :entity-definitions
   {:user {
      :entity-name "User"
      :entity-type :object
      :entity-description "A user"
      :entity-fields [:id :first-name :last-name :age]
      :entity-field-definitions {
       :first-name {
             :name "First Name"
             :type :string
             :length 10
             :display true
             :description "The first name of the user"}
       :id {
             :name "ID"
             :type :integer
             :display false
             :description "The datbase ID"}
       :last-name {
             :name "Last Name"
             :length 20
             :display true
             :description "Last Name"}}}}})
  (tst/is  (= true (s/valid? ::erm/erm erm1))))



(tst/deftest entity-test-1
  (def entity1 {
      :entity-name "User"
      :entity-type :object
      :entity-description "A user"
      :entity-fields [:id :first-name :last-name :age]
      :entity-field-definitions {
       :first-name {
             :name "First Name"
             :type :string
             :length 10
             :display true
             :description "The first name of the user"}
       :id {
             :name "ID"
             :type :integer
             :display false
             :description "The datbase ID"}
       :last-name {
             :name "Last Name"
             :length 20
             :display true
             :description "Last Name"}}} )
  (tst/is (= true  (s/valid? ::erm/entity entity1))))

(tst/deftest entity-test-noname-in-field
  (def entity1 {
      :entity-name "User"
      :entity-type :object
      :entity-description "A user"
      :entity-fields [:id :first-name :last-name :age]
      :entity-field-definitions {
       :first-name {
             :this-isnot-a-name "First Name"
             }
       }})
  (tst/is (= false (s/valid? ::erm/entity entity1))))

(tst/deftest entity-test-no-name-in-entity-definition
  (def entity1 {
      :no-name "User"
      :entity-type :object
      :entity-description "A user"
      :entity-fields [:id :first-name :last-name :age]
      :entity-field-definitions {
       :first-name {
             :name "First Name"
             }
       }})
  (tst/is (= false (s/valid? ::erm/entity entity1))))

(tst/deftest get-entities-from-erm1 
  (def erm1
    {:entities [:user :manager ]
     :entity-definitions
     {:user {
             :entity-name "User"
             :entity-type :object
             :entity-description "A user"
             :entity-fields [:id :first-name :last-name :age]
             :entity-field-definitions {
                :first-name {
                   :name "First Name"
                   :type :string
                   :length 10
                   :display true
                   :description "The first name of the user"}
                :id {
                   :name "ID"
                   :type :integer
                   :display false
                   :description "The datbase ID"}
                :last-name {
                   :name "Last Name"
                   :length 20
                   :display true
                   :description "Last Name"}}}}})


  (tst/is (= [:user :manager] (erm/get-entities erm1 )))
  
  )

(tst/deftest get-fields1 
  (def erm1
    {:entities [:user :manager ]
     :entity-definitions
     {:user {
             :entity-name "User"
             :entity-type :object
             :entity-description "A user"
             :entity-fields [:id :first-name :last-name :age]
             :entity-field-definitions {
                :first-name {
                   :name "First Name"
                   :type :string
                   :length 10
                   :display true
                   :description "The first name of the user"}
                :id {
                   :name "ID"
                   :type :integer
                   :display false
                   :description "The datbase ID"}
                :last-name {
                   :name "Last Name"
                   :length 20
                   :display true
                   :description "Last Name"}}}}})


  (tst/is (= [:id :first-name :last-name :age] (erm/get-fields erm1 :user )))
  
  )

(tst/deftest get-field1 
  (def erm1
    {:entities [:user :manager ]
     :entity-definitions
     {:user {
             :entity-name "User"
             :entity-type :object
             :entity-description "A user"
             :entity-fields [:id :first-name :last-name :age]
             :entity-field-definitions {
                :first-name {
                   :name "First Name"
                   :type :string
                   :length 10
                   :display true
                   :description "The first name of the user"}
                :id {
                   :name "ID"
                   :type :integer
                   :display false
                   :description "The datbase ID"}
                :last-name {
                   :name "Last Name"
                   :length 20
                   :display true
                   :description "Last Name"}}}}})

  (def first-name-def  {
                         :name "First Name"
                         :type :string
                         :length 10
                         :display true
                         :description "The first name of the user"})

  
  (tst/is (= first-name-def (erm/get-field erm1 :user :first-name  ))))


(tst/deftest get-field-name 
  (def erm1
    {:entities [:user :manager ]
     :entity-definitions
     {:user {
             :entity-name "User"
             :entity-type :object
             :entity-description "A user"
             :entity-fields [:id :first-name :last-name :age]
             :entity-field-definitions {
                :first-name {
                   :name "First Name"
                   :type :string
                   :length 10
                   :display true
                   :description "The first name of the user"}}}}})


   (tst/is (= "First Name" (erm/get-field-name erm1 :user :first-name  ))))


  

  
  







(tst/run-tests )

