angular.module('prpoFrontEnd.services', [])
.factory('Izdelki', function($resource) {
	return $resource('http://localhost:8080/prpo-trgovina-rest/v1/izdelki/:id', { id: '@id' }, {
		update: {
			method: 'PUT'
		}
	});
})
.factory('Narocila', function($resource) {
	return $resource('http://localhost:8080/prpo-trgovina-rest/v1/narocila/:id', { id: '@id' }, {
		update: {
			method: 'PUT'
		}
	});
})
.factory('Avto', function($resource) {
	return $resource('http://localhost:8080/test/v1/avto/:avtoId', { avtoId: '@avtoId' }, {
		update: {
			method: 'PUT'
		}
	});
})
.factory('AvtoIzposoja', function($resource) {
	return $resource('http://localhost:8080/test/v1/avto/:avtoId/izposoja', { avtoId: '@avtoId' }, {
		update: {
			method: 'PUT'
		}
	});
})
.factory('Izposoja', function($resource) {
	return $resource('http://localhost:8080/test/v1/izposoja/:izposojaId', { izposojaId: '@izposojaId' }, {
		update: {
			method: 'PUT'
		}
	});
})
.factory('Voznik', function($resource) {
	return $resource('http://localhost:8080/test/v1/voznik/:voznikId', { voznikId: '@voznikId' }, {
		update: {
			method: 'PUT'
		}
	});
});