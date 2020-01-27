function recupererClients() {

	fetch('clients/lister')

			.then(function(resp) {

				return resp.json();

			})

			.then(

					function(listeClient) {

						var client = listeClient.map(

						function(client) {

							return '<option>' + client.nom + " "

							+ client.prenoms + '</option>';

						}).join();

						document.getElementById('client').innerHTML = ' <label for="client">client</label><select class="form-control" name="client" id="client">'

								+ client + '</select>';

					})

}

function recupererChambres() {

	fetch('chambre')

			.then(function(resp) {

				return resp.json();

			})

			.then(

					function(listeChambre) {

						var chambre = listeChambre
								.map(

										function(chambre) {

											return '<div class="row"><input class="form-check-input" type="checkbox" id="'

													+ chambre.uuid

													+ '" ><label class="form-check-label" for="'

													+ chambre.uuid

													+ '">'

													+ chambre.numero

													+ " ("

													+ chambre.surfaceEnM2

													+ " m²) - Hotel "
													+ chambre.hotel.nom
													+ " ("
													+ chambre.hotel.nombreEtoiles
													+ " etoile(s)"

													+ '</label></div>';

										}).join();

						document.getElementById('cham').innerHTML = '<div class="form-group form-check">'
								+ chambre + '</div>';

					})

}

function ajoutBDD() {

	var resa = {

	}

}

fetch('reservations', {

	method : 'POST',

	body : ''

})}
