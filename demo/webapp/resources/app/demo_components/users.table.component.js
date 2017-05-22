(function() {
    'use strict';
	angular.
	  module('demo').
	  component('usersTable', {
	    templateUrl: 'resources/app/demo_templates/users.table.template.html',
	    controller: function UsersListController(UserService, $uibModal, toastr, toastrConfig) {
	    	
	      //Toastr config
	      angular.extend(toastrConfig, {	    	   
	       positionClass: 'toast-bottom-right',	    	    
	      });	    	
	    	
	      var self = this;	      
	      self.users = UserService.query();  	    
	      var $ctrl = this;		      
	      $ctrl.orderByField = 'firstName';
	      $ctrl.reverseSort = false;	      
	      $ctrl.user = {};
	      $ctrl.animationsEnabled = true;

	      $ctrl.open = function (userId) {	        
	        var modalInstance = $uibModal.open({
	          animation: $ctrl.animationsEnabled,
	          ariaLabelledBy: 'modal-title',
	          ariaDescribedBy: 'modal-body',
	          templateUrl: 'UserModal.html',
	          controller: function InstanceController($uibModalInstance, UserService) {
	        	  var $ctrl = this;	        	  
	        	  $ctrl.parseDate = function(str1) {			  	    	
			  	    	var yr1   = parseInt(str1.substring(0,4));
			  	    	var mon1  = parseInt(str1.substring(5,7));
			  	    	var dt1   = parseInt(str1.substring(8,10));
			  	    	var date1 = new Date(yr1, mon1-1, dt1);
			  	    	return date1;
		  	    	  };  	    	  
	        	  
	        	  if (userId && userId!==null) {
	        		  $ctrl.mode = 'Edit';
		        	} else {
		        	  $ctrl.mode = 'Add';
		        	}
	    		  
	        	  if (userId && userId!==null) {
	        		  $ctrl.user = UserService.get({id:userId},function(successResult) {
	        			  $ctrl.user.dateOfBirth = $ctrl.parseDate($ctrl.user.dateOfBirth);
  	    	  	    	    }, function(errorResult) {
  	    	  	    	        // do something on error
  	    	  	    	        if(errorResult.status === 404) {            
  	    	  	    	        }
  	    	  	    	    });
	        	  }

	    		  $ctrl.ok = function () {
	    		    $uibModalInstance.close($ctrl.selected.item);
	    		  };

	    		  $ctrl.cancel = function () {
	    		    $uibModalInstance.dismiss('cancel');
	    		  };    
	    		  
	    		  $ctrl.saveForm = function () {
	    	    	  console.log('Saving...')
	    	    	  console.log('user: '+$ctrl.user);
	    	    	  
	    	    	  var da = $ctrl.user.dateOfBirth;
	    	    	  var month = da.getMonth() + 1
	    	    	  
	    	    	  if(month < 10) {
	    	    		  month = '0'+month;
	    	    	  }
	    	    	  var day = da.getDate();
	    	    	  if(day < 10) {
	    	    		  day = '0'+day;
	    	    	  }
	    	    	  var year = da.getFullYear();
	    	    	  var shortStartDate = day + "-" + month + "-" + year;
	    	    	  
	    	    	  $ctrl.user.dateOfBirth = shortStartDate;	    	    	  
	    	    	  
	    	    	  if($ctrl.user.id && $ctrl.user.id !== null) { //EDIT
	    	    		  return UserService.update({id:$ctrl.user.id},$ctrl.user, 
	  	    	  	    	    function(successResult) {
	  	    	  	    		   self.users = UserService.query();
	  	    	  	    		   $ctrl.cancel();
	  	    	  	    		   toastr.info($ctrl.user.firstName + ' ' + $ctrl.user.lastName, 'A user has changed...');
	  	    	  	    	    }, function(errorResult) {
                                    $ctrl.cancel();
	  	    	  	    	        toastr.error("Error occured!Try again.");
	  	    	  	    	    });
	    	    		  
	    	    	  } else { //SAVE
	    	    		  return UserService.save($ctrl.user, 
	  	    	  	    	    function(successResult) {
	  	    	  	    		   self.users = UserService.query();
	  	    	  	    		   $ctrl.cancel();
	  	    	  	    		   toastr.info($ctrl.user.firstName + ' ' + $ctrl.user.lastName, 'A new user was added...');
	  	    	  	    	    }, function(errorResult) {
                                    $ctrl.cancel();
	  	    	  	    	        toastr.error("Error occured!Try again.");
	  	    	  	    	    });
	    	    	  }	    	    	  
	    	      }	    		  
	    		  
	    	      $ctrl.today = function() {	    	    	
	    	    	  $ctrl.dt = new Date();	    	    	   	    	    
	  	    	  };
	  	    	  $ctrl.today();
                  
	  	    	  $ctrl.clear = function() {
	  	    	    $ctrl.dt = null;
	  	    	  };

	  	    	  $ctrl.inlineOptions = {
	  	    	    customClass: getDayClass,
	  	    	    minDate: new Date(),
	  	    	    showWeeks: true
	  	    	  };

	  	    	  $ctrl.dateOptions = {
	  	    	    dateDisabled: false,
	  	    	    formatYear: 'yy',
	  	    	    maxDate: new Date(),
	  	    	    minDate: new Date(),
	  	    	    startingDay: 1
	  	    	  };

	  	    	  // Disable weekend selection
	  	    	  function disabled(data) {
	  	    	    var date = data.date,
	  	    	      mode = data.mode;
	  	    	    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
	  	    	  }

	  	    	  $ctrl.toggleMin = function() {
	  	    	    $ctrl.inlineOptions.minDate = $ctrl.inlineOptions.minDate ? null : new Date();
	  	    	    $ctrl.dateOptions.minDate = $ctrl.inlineOptions.minDate;
	  	    	  };

	  	    	  $ctrl.toggleMin();

	  	    	  $ctrl.open1 = function() {
	  	    	    $ctrl.popup1.opened = true;
	  	    	  };

	  	    	  $ctrl.open2 = function() {
	  	    	    $ctrl.popup2.opened = true;
	  	    	  };

	  	    	  $ctrl.setDate = function(year, month, day) {
	  	    	    $ctrl.dt = new Date(year, month, day);
	  	    	  };

	  	    	  $ctrl.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	  	    	  $ctrl.format = $ctrl.formats[0];
	  	    	  $ctrl.altInputFormats = ['M!/d!/yyyy'];

	  	    	  $ctrl.popup1 = {
	  	    	    opened: false
	  	    	  };

	  	    	  $ctrl.popup2 = {
	  	    	    opened: false
	  	    	  };

	  	    	  var tomorrow = new Date();
	  	    	  tomorrow.setDate(tomorrow.getDate() + 1);
	  	    	  var afterTomorrow = new Date();
	  	    	  afterTomorrow.setDate(tomorrow.getDate() + 1);
	  	    	  $ctrl.events = [
	  	    	    {
	  	    	      date: tomorrow,
	  	    	      status: 'full'
	  	    	    },
	  	    	    {
	  	    	      date: afterTomorrow,
	  	    	      status: 'partially'
	  	    	    }
	  	    	  ];

	  	    	  function getDayClass(data) {
	  	    	    var date = data.date,
	  	    	      mode = data.mode;
	  	    	    if (mode === 'day') {
	  	    	      var dayToCheck = new Date(date).setHours(0,0,0,0);

	  	    	      for (var i = 0; i < $ctrl.events.length; i++) {
	  	    	        var currentDay = new Date($ctrl.events[i].date).setHours(0,0,0,0);

	  	    	        if (dayToCheck === currentDay) {
	  	    	          return $ctrl.events[i].status;
	  	    	        }
	  	    	      }
	  	    	    }

	  	    	    return '';
	  	    	  }
	          },
	          controllerAs: '$ctrl',	          
	          resolve: {
	            items: function () {
	              return $ctrl.items;
	            }
	          }
	        });

	        modalInstance.result.then(function (selectedItem) {
	          $ctrl.selected = selectedItem;
	        }, function () {
	          $log.info('Modal dismissed at: ' + new Date());
	        });
	      };

	      $ctrl.delete = function (userId) {    	  
	    	  swal({   title: "Are you sure?",   
					 text: "You will devestate this user's hopes and dreams...!",   
					 type: "warning",   
					 showCancelButton: true,   
					 confirmButtonColor: "#DD6B55",   
					 confirmButtonText: "Yes, delete it!",   
					 cancelButtonText: "No, cancel!",   
					 closeOnConfirm: true,   
					 closeOnCancel: false }, 
					 
					function(isConfirm){   
							if (isConfirm) {  
								return UserService.delete({id:userId}, 
							    	    function(successResult) {
									       toastr.success('Another one bites the dust...');
							    		   self.users = UserService.query();
							    	    }, function(errorResult) {                                        
							    	        toastr.error(errorResult.data.errorMessage, "Deletion failed!"); 
							    	    });								
							} else {     swal("Cancelled", "The user escapes deletion and thanks you kindly :)", "error");   } });
	      }
	    }
	  });
})();