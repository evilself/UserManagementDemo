(function() {
    'use strict';
	angular.
	  module('demo').
	  component('sectionTech', {
	    templateUrl: 'resources/app/demo_templates/section.tech.template.html',
	    controller: TechController
	  });
    
    function TechController($scope) {             
        $scope.technologies = [
          {
            timelineClass : "",
            img : "./resources/img/tech/java8.png",
            heading : "Java",
            subheading : "8",
            description : "Stream processing and lampdas..."
          },
            {
            timelineClass : "timeline-inverted",
            img : "./resources/img/tech/angular.png",
            heading : "AngularJS",
            subheading : "1.6.4",
            description : "AngularJS with Angular-Resource as the REST client and libraries such as Angular-Toastr adding a bit of style"
          },
            {
            timelineClass : "",
            img : "./resources/img/tech/spring.png",
            heading : "Spring",
            subheading : "Spring4, SpringMVC, SpringDataJPA",
            description : "Provides the guts of my app(IoC) - MVC for the REST services, DataJPA for CRUD persistence operations"
          },
            {
            timelineClass : "timeline-inverted",
            img : "./resources/img/tech/html.jpg",
            heading : "HTML/CSS/JS",
            subheading : "There is no frontend without these BIG three",
            description : "Adding style and functionality to my template"
          },
            {
            timelineClass : "",
            img : "./resources/img/tech/hibernate.png",
            heading : "ORM",
            subheading : "Hibernate/JPA with MySQL",
            description : "Just because i didn't want to deal with low level JDBC and SQL operations"
          },
            {
            timelineClass : "timeline-inverted",
            img : "./resources/img/tech/bootstrap.jpg",
            heading : "Bootstrap",
            subheading : "Why worry about coming up with great CSS classes when we have Bootstrap?",
            description : "RESPONSIVE DESIGN"
          }
        ];        
    }
})();