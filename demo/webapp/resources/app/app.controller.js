(function() {
    'use strict';

    angular
        .module('demo')
        .controller('SetupController', SetupController);

    function SetupController() {    	
        var $ctrl = this;
        $ctrl.global = {
          company: "Westernacher",
          title: "Welcome To My Demo!",
          subtitle: "_hope you like it_"       
        };    		 
    }
})();