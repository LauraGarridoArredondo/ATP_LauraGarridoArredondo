/**
 *
 */

$(document).ready(function(){

    $.ajax({
        url: 'http://localhost:4200',
        type: 'GET',
        success: function(data) {
            var html = "";
            $.each(data, function(index, value){
                html += '<tr>';
                html += '<td>'+ value.id+'</td>';
                html += '<td>'+ value.ranking+'</td>';
                html += '<td>'+ value.nombreCompleto+'</td>';
                html += '<td>'+ value.pais+'</td>';
                html += '<td>'+ value.fechaNacimiento+'</td>';
                html += '<td>'+ value.edad+'</td>';
                html += '<td>'+ value.altura+'</td>';
                html += '<td>'+ value.peso+'</td>';
                html += '<td>'+ value.debutProfesional+'</td>';
                html += '<td>'+ value.mano+'</td>';
                html += '<td>'+ value.reves+'</td>';
                html += '<td>'+ value.entrenador+'</td>';
                html += '<td>'+ value.dineroGanado+'</td>';
                html += '<td>'+ value.mejorRanking+'</td>';
                html += '<td>'+ value.numVictorias+'</td>';
                html += '<td>'+ value.numDerrotas+'</td>';
                html += '<td>'+ value.imagen+'</td>';
                html += '</tr>';
            });
            $("#tenistas-table-body").append(html);
        },
        error: function(error) {
            console.log("Error: " + error);
        }
    });

});