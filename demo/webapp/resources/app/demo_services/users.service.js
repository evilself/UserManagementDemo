(function() {
    'use strict';
    
      angular.
         module('demo').
         factory('UserService', ['$resource',
           function($resource) {
             return $resource('/demo/users/:id.json', null, {
               query: {
                 method: 'GET',
                 params: {id: ''},
                 isArray: true
               },
               update : { 
                   method:'PUT' 
               }
             });
           }
         ]);
})();