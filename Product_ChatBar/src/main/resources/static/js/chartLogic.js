	var productLabels = /*[[${productLabels}]]*/[];
		var productData = /*[[${productData}]]*/[];
		console.log(productLabels);

		var ctx = document.getElementById('myChart');
		var currentChart;
		function showBarChart() {
			console.log("method called");
			if (currentChart) {
				currentChart.destroy();
			}
			currentChart = new Chart(ctx, {
				type: 'bar',
				data: {
					labels: productLabels,
					datasets: [{
						label: 'Product Prices',
						data: productData,
						backgroundColor: 'rgba(54, 162, 235, 0.5)',
						borderColor: 'rgba(54, 162, 235, 1)',
						borderWidth: 1,
						barThickness: 10,
						maxBarThickness: 12
					}]
				},
				options: {
					// Chart options
				}
			});
		}
		function showPieChart() {
			if (currentChart) {
				currentChart.destroy();
			}
			currentChart = new Chart(ctx, {
				type: 'pie',
				data: {
					labels: ['Expensive', 'Inexpensive', 'MediumPrduct', 'CheapProduct'],
					datasets: [{
						label: 'Product Prices',
						data: [
				  /*[[${expensiveCount}]]*/,
				  /*[[${inexpensiveCount}]]*/,
				  /*[[${cheapCount}]]*/,
							/*[[${mediumCount}]]*/
						],
						backgroundColor: [
							'rgba(255, 99, 132, 0.5)', // Red
							'rgba(54, 162, 235, 0.5)', // Blue
							'rgba(255, 206, 86, 0.5)', // Yellow
							'rgba(75, 192, 192, 0.5)',

						],
						borderColor: [
							'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)'

						],
						borderWidth: 1
					}]
				},
				options: {

				}
			});
		}

const products = /*[[${products}]]*/[];
		const productsPerPage = 5;
		let currentPage = 1;
		// Function to render products for the specified page
		function renderProducts(page) {
			const productList = document.getElementById('productBody');
			productList.innerHTML = '';
			const startIndex = (page - 1) * productsPerPage;
			const endIndex = Math.min(startIndex + productsPerPage, products.length);
			for (let i = startIndex; i < endIndex; i++) {
				const product = products[i];
				const row = `<tr class="productRow">
					<td>${product.id}</td>
					<td>${product.name}</td>
					 <td>${product.price}</td>
					  <td>
                                    <a href="/show/${product.id}" class="btn btn-primary btn-sm">View</a>
                                    <a href="/edit/${product.id}" class="btn btn-info btn-sm">Edit</a>
                                    <a href="/delete/${product.id}" class="btn btn-danger btn-sm">Delete</a>
                                </td>
					</tr >`;
				productList.insertAdjacentHTML('beforeend', row);
			}
		}
		// Function to render pagination links
		function renderPagination() {
			const totalPages = Math.ceil(products.length / productsPerPage);
			const pagination = document.getElementById('pagination');
			pagination.innerHTML = '';
			for (let i = 1; i <= totalPages; i++) {
				const link = document.createElement('a');
				link.href = "#";
				link.textContent = i;
				link.addEventListener('click', function (event) {
					event.preventDefault();
					currentPage = i;
					renderProducts(currentPage)
				});
				pagination.appendChild(link);
			}
		}

		// Initial rendering on page load
		window.onload = function () {
			renderProducts(currentPage);
			renderPagination();
		};

	