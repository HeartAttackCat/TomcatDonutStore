
function getMenu() {
	const url = "./api/menu";
    fetch(url).then(
        function accept(response) {
            var menujson = response.json();
        },
        function reject(reason) {
			console.log("Error server did not respond.")
        }
    )
}


console.log("Hello world");
getMenu();