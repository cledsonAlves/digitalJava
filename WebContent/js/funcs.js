//  Função para remover / incluir campos
$(function() {
	function removeCampo() {
		$(".removerCampo").unbind("click");
		$(".removerCampo").bind("click", function() {
			i = 0;
			$(".telefones p.campoTelefone").each(function() {
				i++;
			});
			if (i > 1) {
				$(this).parent().remove();
			}
		});
	}
	removeCampo();
	$(".adicionarCampo").click(function() {
		novoCampo = $(".telefones p.campoTelefone:first").clone();
		novoCampo.find("input").val("");
		novoCampo.insertAfter(".telefones p.campoTelefone:last");
		removeCampo();
	});
});

//./

$(function() {
	$('.input').tooltip({
		placement : 'left',
		title : 'Required field'
	});
});

// Função para validar inputs
$(document)
		.ready(
				function() {
					$(
							'.input-group input[required], .input-group textarea[required], .input-group select[required]')
							.on(
									'keyup change',
									function() {
										var $form = $(this).closest('form'), $group = $(
												this).closest('.input-group'), $addon = $group
												.find('.input-group-addon'), $icon = $addon
												.find('span'), state = false;

										if (!$group.data('validate')) {
											state = $(this).val() ? true
													: false;
										} else if ($group.data('validate') == "email") {
											state = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
													.test($(this).val())
										} else if ($group.data('validate') == 'phone') {
											state = /^[(]{0,1}[0-9]{3}[)]{0,1}[-\s\.]{0,1}[0-9]{3}[-\s\.]{0,1}[0-9]{4}$/
													.test($(this).val())
										} else if ($group.data('validate') == "length") {
											state = $(this).val().length >= $group
													.data('length') ? true
													: false;
										} else if ($group.data('validate') == "number") {
											state = !isNaN(parseFloat($(this)
													.val()))
													&& isFinite($(this).val());
										}

										if (state) {
											$addon.removeClass('danger');
											$addon.addClass('success');
											$icon.attr('class',
													'glyphicon glyphicon-ok');
										} else {
											$addon.removeClass('success');
											$addon.addClass('danger');
											$icon.attr('class',
													'glyphicon glyphicon-');
										}

										// desabilita o submit
										/***************************************
										 * if
										 * ($form.find('.input-group-addon.danger').length ==
										 * 0) {
										 * $form.find('[type="submit"]').prop('disabled',
										 * false); }else{
										 * $form.find('[type="submit"]').prop('disabled',
										 * true); }
										 **************************************/
									});

					$(
							'.input-group input[required], .input-group textarea[required], .input-group select[required]')
							.trigger('change');

				});

(function($) {
	$(function() {

		var addFormGroup = function(event) {
			event.preventDefault();

			var $formGroup = $(this).closest('.form-group');
			var $multipleFormGroup = $formGroup.closest('.multiple-form-group');
			var $formGroupClone = $formGroup.clone();

			$(this).toggleClass('btn-success btn-add btn-danger btn-remove')
					.html('–');

			$formGroupClone.find('input').val('');
			$formGroupClone.find('.concept').text('Phone');
			$formGroupClone.insertAfter($formGroup);

			var $lastFormGroupLast = $multipleFormGroup
					.find('.form-group:last');
			if ($multipleFormGroup.data('max') <= countFormGroup($multipleFormGroup)) {
				$lastFormGroupLast.find('.btn-add').attr('disabled', true);
			}
		};

		var removeFormGroup = function(event) {
			event.preventDefault();

			var $formGroup = $(this).closest('.form-group');
			var $multipleFormGroup = $formGroup.closest('.multiple-form-group');

			var $lastFormGroupLast = $multipleFormGroup
					.find('.form-group:last');
			if ($multipleFormGroup.data('max') >= countFormGroup($multipleFormGroup)) {
				$lastFormGroupLast.find('.btn-add').attr('disabled', false);
			}

			$formGroup.remove();
		};

		var selectFormGroup = function(event) {
			event.preventDefault();

			var $selectGroup = $(this).closest('.input-group-select');
			var param = $(this).attr("href").replace("#", "");
			var concept = $(this).text();

			$selectGroup.find('.concept').text(concept);
			$selectGroup.find('.input-group-select-val').val(param);

		}

		var countFormGroup = function($form) {
			return $form.find('.form-group').length;
		};

		$(document).on('click', '.btn-add', addFormGroup);
		$(document).on('click', '.btn-remove', removeFormGroup);
		$(document).on('click', '.dropdown-menu a', selectFormGroup);

	});
})(jQuery);
