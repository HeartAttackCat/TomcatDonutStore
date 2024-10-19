
function getMenu() {
	const url = "./api";
    fetch(url)
	.then(response => response.json())
	.then(table => {
		table.forEach(row => {
			makeMenu(row);
		});
	}
	).catch (error => {
		console.log("Something went wrong.");
	})
}

function makeMenu(object) {
	var parent = document.getElementById("content");
	var container = document.createElement("div");
	var d_name = document.createElement("h3");
	var d_flavor = document.createElement("h4");
	var d_price = document.createElement("h4");
	container.classList.add("donut");
	d_name.textContent = object["dType"];
	d_price.textContent = "$" + object["price"];
	d_flavor.textContent = object["flavor"];
	container.appendChild(d_name);
	container.appendChild(d_flavor);
	container.appendChild(d_price);
	parent.append(container);
}

console.log("Hello world");
getMenu(1);