$(document).ready(function() {
    $('#salas').hide();
    $('input[type="radio"]').click(function() {
        if($(this).attr('id') == 'inlineRadio2') {
            $('#salas').show();
            $('#habitaciones').hide();
            $('#reservationForm').attr('action','newMeetingRoomReservation');
        }
        else
        {
            $('#salas').hide();
            $('#habitaciones').show();
            $('#reservationForm').attr('action','newReservation');
        }
    });
});