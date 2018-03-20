angular
    .module('coworkingApp').service("Notify", ['ngDialog', 
    function(ngDialog){
        this.openModal = function(template, data, width) {
            return ngDialog.open({
                template: template,
                data: data,
                width: width 
            });
        }
    }
])