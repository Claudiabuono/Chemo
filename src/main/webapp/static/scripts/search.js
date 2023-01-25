function expandSearchFilters() {
    document.getElementById("search-filters").className = "";
    document.getElementById("search-filters-button").setAttribute( "value", "Riduci filtri");
    document.getElementById("search-filters-button").onclick = reduceSearchFilters;
}
function reduceSearchFilters() {
    document.getElementById("search-filters").className = "hidden";
    document.getElementById("search-filters-button").setAttribute( "value", "Espandi filtri");
    document.getElementById("search-filters-button").onclick = expandSearchFilters;
}