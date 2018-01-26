(ns hello-fig.test
  (:require  
   [hello-fig.erutils.core :as er]
   ) )

(def erm-test

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
             :description "Last Name"}}}

    }}    )

