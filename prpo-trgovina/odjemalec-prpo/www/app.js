app = angular.module('prpoFrontEnd', ['ngRoute', 'ngAnimate','ngMaterial','ngResource','prpoFrontEnd.services'])
.config(['$routeProvider', '$locationProvider',
	function($routeProvider, $locationProvider) {

		$routeProvider
		.when('/', {
			templateUrl: 'first.html',
			controller: 'FirstCtrl',
			controllerAs: 'first'
		})
        .when('/izdelki', {
			templateUrl: 'izdelki/izdelkiList.html',
			controller: 'IzdelkiListCtrl',
			controllerAs: 'izdelkiList'
		})
        .when('/izdelek/:id', {
			templateUrl: 'izdelki/izdelek.html',
			controller: 'IzdelekCtrl',
			controllerAs: 'izdelek'
		})
        .when('/izdelek/:id/edit', {
			templateUrl: 'izdelki/izdelekEdit.html',
			controller: 'IzdelekEditCtrl',
			controllerAs: 'izdelekEdit'
		})
		.when('/narocila', {
			templateUrl: 'narocila/narocilaList.html',
			controller: 'NarocilaListCtrl',
			controllerAs: 'narocilaList'
		})
        .when('/add/izdelek', {
			templateUrl: 'izdelki/izdelekAdd.html',
			controller: 'IzdelekAddCtrl',
			controllerAs: 'izdelekAdd'
		})
		.otherwise({
			redirectTo: '/'
		});

		$locationProvider.html5Mode(false);
		$locationProvider.hashPrefix('!');
	}])
.controller('AppCtrl', function ($scope, $timeout, $mdSidenav, $log, $location) {

	$scope.toggleLeft = buildDelayedToggler('left');
	/*
	 * Supplies a function that will continue to operate until the
	 * time is up.
	 */
	 function debounce(func, wait, context) {
		var timer;
		return function debounced() {
			var context = $scope,
			args = Array.prototype.slice.call(arguments);
			$timeout.cancel(timer);
			timer = $timeout(function() {
				timer = undefined;
				func.apply(context, args);
			}, wait || 10);
		};
	 }
	/**
	 * Build handler to open/close a SideNav; when animation finishes
	 * report completion in console
	 */
	 function buildDelayedToggler(navID) {
		return debounce(function() {$mdSidenav(navID).toggle()}, 200);
	 }
})
.controller('FirstCtrl', ['$routeParams', function($routeParams) {

	this.name = "FirstCtrl";
	this.params = $routeParams;

}])
.controller('IzdelekCtrl', function($routeParams,$scope,$location,Izdelki,$mdDialog) {

	this.name = "IzdelekCtrl";
	this.params = $routeParams;
    
	Izdelki.get({ id: $routeParams.id }).$promise.then(function(izdelek) {
		$scope.izdelek = izdelek;
        //console.log($scope.izdelek);
	});
    
    $scope.showConfirm = function(ev) {
	// Appending dialog to document.body to cover sidenav in docs app
		var confirm = $mdDialog.confirm()
			.title('Ali res želiš izbrisati ta zapis?')
			.textContent('Izdelek ob potrditvi ne bo več obstajal.')
			.ariaLabel('Izbris')
			.targetEvent(ev)
			.ok('Izbriši')
			.cancel('Prekliči');
			
		$mdDialog.show(confirm).then(function() {
			$scope.izbrisiIzdelek();
		}, function(){});
	};

	$scope.izbrisiIzdelek = function(){
		$scope.izdelek.$delete(function() {
			$location.path('/izdelki'); // on success go back to home i.e. movies state.
		});
	}

})
.controller('IzdelekEditCtrl', function($routeParams,$scope,$location, Izdelki) {

	this.name = "IzdelekEditCtrl";
	this.params = $routeParams;
    
	Izdelki.get({ id: $routeParams.id }).$promise.then(function(izdelek) {
		$scope.izdelek = izdelek;
	});
    $scope.opozorilo="Tuki";
    $scope.editIzdelekCheck = function(){
        if($scope.izdelek == null) {
            return false;
        }
        if ($scope.izdelek.ime == "") {
            $scope.opozorilo = "Popravi naziv izdelka.";
            return false;
        }
        if ($scope.izdelek.kategorija == "") {
            $scope.opozorilo = "Popravi polje kategorije";
            return false;
        }
        if ($scope.izdelek.cena < 0 || $scope.izdelek.cena == "") {
            $scope.opozorilo = "Popravi polje cena";
            return false;
        }
        if ($scope.izdelek.zaloga < 0 || $scope.izdelek.zaloga == "") {
            $scope.opozorilo = "Popravi polje zaloga";
            return false;
        }
        return true;
    }
    
	$scope.urediIzdelek=function(){
		$scope.izdelek.$update(function() {
			$location.path('/izdelek/'+$routeParams.id); // on success go back to home i.e. movies state.
		});
	}

})
.controller('IzdelkiListCtrl', function($routeParams,$scope,Izdelki) {

	this.name = "IzdelkiListCtrl";
	this.params = $routeParams;

	$scope.allIzdelki = []
	$scope.pages = [1]
	$scope.currentPage = 1
	$scope.numPerPage = 3
	$scope.maxSize = 3;
	$scope.order = "id";
	$scope.ascending = "asc";
	$scope.sort = false;
	$scope.advancedSearch = false;
	$scope.searchIme = "";
	$scope.searchKategorija = "";
	$scope.searchCena = "";
	$scope.cenaPredznak = "lte";
	$scope.datumPredznak = "lte";
	$scope.datumSearch = "";
	
	$scope.setCenaPredznak = function(newCenaPredznak){
		$scope.cenaPredznak = newCenaPredznak;
		console.log($scope.order);
//		console.log($scope.datumSearch.setHour($scope.datumSearch.getHour() + 1));
//		console.log($scope.datumSearch.getTime());
	}
	
	$scope.setDatumPredznak = function(newDatumPredznak){
		$scope.datumPredznak = newDatumPredznak;
//		console.log($scope.datumSearch.getTime());
	}
	
	$scope.setOrder = function(newOrder){
		$scope.order = newOrder;
		console.log($scope.order);
		
	}

	$scope.setAscending = function(newAsc){
		$scope.ascending = newAsc;
		console.log($scope.ascending);
	}

	$scope.whatClassIsIt= function(someValue){
		if(someValue==$scope.pages[0])
			return "left"
		else if(someValue==$scope.pages[$scope.pages.length-1])
			return "right";
		else
			return "middle";
	}

	$scope.loadFunction = function(page) {
		
		var start = (page-1)*$scope.numPerPage;
		
		var whereCond = "";
		if($scope.searchIme != null && $scope.searchIme.trim() != "") {
			whereCond += "ime:" + $scope.searchIme + ",";
		}
		if($scope.searchKategorija != null && $scope.searchKategorija.trim() != "") {
			whereCond += "kategorija:" + $scope.searchKategorija + ",";
		}
		if($scope.searchCena != null && $scope.searchCena != "") {
			whereSign = $scope.cenaPredznak == "eq" ? "" : $scope.cenaPredznak + ":";
			whereCond += "cena:" + whereSign + $scope.searchCena + ",";
		}
		if($scope.datumSearch != null && $scope.datumSearch != "") {
			whereCond += "datum:" + $scope.datumPredznak + ":" + $scope.datumSearch.getFullYear() + "-" + ($scope.datumSearch.getMonth() + 1) + "-" + $scope.datumSearch.getDate() + ",";
		}
		if(whereCond != "") {
			whereCond = whereCond.substring(0, whereCond.length - 1);
		}

		Izdelki.query({limit: $scope.numPerPage, offset: start, order: $scope.order + ' ' + $scope.ascending, where: whereCond}, function(vsiIzdelki, getResponseHeaders) {

			$scope.izdelekNum = getResponseHeaders("X-Total-Count");
			$scope.pages = [1];
			$scope.allIzdelki = [];
            
			if($scope.allIzdelki.length == 0) {
				for(i = 0; i<$scope.izdelekNum; i++) {
					if(i>= start && i<start+$scope.numPerPage) {
						$scope.allIzdelki.push(vsiIzdelki[i-start]);
					} else {
						$scope.allIzdelki.push(i.toString());
					}
				}
				$scope.izdelki = $scope.allIzdelki.slice(0, $scope.numPerPage);
			}
//			console.log($scope.izdelki);

			for (i=0;i<vsiIzdelki.length;i++) {
//				if(typeof $scope.allIzdelki[start+i] === 'string') {
					$scope.allIzdelki[start+i] = vsiIzdelki[i];
//				}
			}
//			console.log("VNATRE");
//			console.log($scope.allIzdelki);
			$scope.izdelki = $scope.allIzdelki.slice(start, start + $scope.numPerPage);

			if($scope.pages.length == 1 && $scope.izdelekNum > $scope.numPerPage){
				for(i=2; i<= Math.ceil($scope.izdelekNum/$scope.numPerPage);i++){
					$scope.pages.push(i);
				}
			}
            
			$scope.currentPage=page;
		});
	}
	
	$scope.naprednoIskanje = function() {
		var begin = 0, end = $scope.numPerPage;
		$scope.loadFunction(1);
	}

	$scope.$watch("currentPage + numPerPage + order + ascending", function() {
		var begin = (($scope.currentPage - 1) * $scope.numPerPage), end = begin + $scope.numPerPage;
		$scope.loadFunction($scope.currentPage)
//		$scope.izdelki = $scope.allIzdelki.slice(begin, end);
	});

})
.controller('NarocilaListCtrl', function($routeParams,$scope,Narocila) {

	this.name = "NarocilaListCtrl";
	this.params = $routeParams;

	$scope.narocila = Narocila.query();
})
.controller('IzdelekAddCtrl', function($routeParams,$scope,$location, Izdelki) {

	this.name = "IzdelekAddCtrl";
	this.params = $routeParams;

	var now = new Date();
	$scope.minDate = new Date(now.getFullYear(), now.getMonth(), now.getDate());
    
    $scope.opozorilo = "Opozorilo";
    
    $scope.saveIzdelekCheck = function(){
        if($scope.izdelek == null | $scope.izdelek == "") {
            return false;
        }
        if ($scope.izdelek.ime == null | $scope.izdelek.ime == "") {
            $scope.opozorilo = "Dodaj naziv izdelka.";
            return false;
        }
        if ($scope.izdelek.kategorija == null | $scope.izdelek.kategorija == "") {
            $scope.opozorilo = "Dodaj polje kategorije";
            return false;
        }
        if ($scope.izdelek.cena < 0 | $scope.izdelek.cena == null | $scope.izdelek.cena == "") {
            $scope.opozorilo = "Dodaj polje cena";
            return false;
        }
        if ($scope.izdelek.zaloga < 0 | $scope.izdelek.zaloga == null | $scope.izdelek.zaloga == "") {
            $scope.opozorilo = "Dodaj polje zaloga";
            return false;
        }
        if ($scope.izdelek.davek < 5 | $scope.izdelek.davek == null | $scope.izdelek.davek == "") {
            $scope.opozorilo = "Dodaj polje davek";
            return false;
        }
        return true;
    }
    $scope.saveIzdelek = function(izd) {
        var data = JSON.parse(JSON.stringify(izd));
        if(izd.datum != null) {
            data.datum = (izd.datum.getFullYear() + '-' + (izd.datum.getMonth() + 1)  + '-' +  izd.datum.getDate() );
        }
        console.log(data);
        Izdelki.save(data, function() {
            $location.path('/izdelki');
        }); 
    }
})

var INTEGER_REGEXP = /^\-?\d+$/;
app.directive('integer', function() {
  return {
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {
      ctrl.$validators.integer = function(modelValue, viewValue) {
        if (ctrl.$isEmpty(modelValue)) {
          return true;
        }

        if (INTEGER_REGEXP.test(viewValue)) {
          return true;
        }

        return false;
      };
    }
  };
});

app.directive('stikalo', function() {
    var directive = {};

    directive.restrict = 'EA'; /* restrict this directive to elements */

    directive.template = '<div><md-input-container><md-checkbox aria-label="Checkbox" ng-true-value="1" ng-false-value="0" flex>Takoj na zalogi</md-input-container></div>';

    return directive;
});