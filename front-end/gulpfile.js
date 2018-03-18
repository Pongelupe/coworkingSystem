var gulp = require('gulp')
var concat = require('gulp-concat');

var paths = {
    js: [
        "node_modules/jquery/dist/jquery.js",
        "node_modules/bootstrap/dist/js/bootstrap.bundle.js",
        "node_modules/angular/angular.js",
        "node_modules/@uirouter/angularjs/release/angular-ui-router.js",
        "node_modules/angular-loading-bar/src/loading-bar.js",
        "node_modules/angular-animate/angular-animate.js"
    ],
    css: [
        "node_modules/bootstrap/dist/css/bootstrap.css",
        "node_modules/components-font-awesome/css/font-awesome.css"
    ]
};


//Tarefa para copiar as fonts do font-awesome para pasta src/shared/libs/js
gulp.task('libs-fonts', function () {
    gulp.src('node_modules/components-font-awesome/fonts/**/*')
        .pipe(gulp.dest('src/shared/libs/fonts'));
});

//Tarefa para concatenar as libs js public e salvar arquivo em src/shared/libs/js
gulp.task('libs-js', function () {
    gulp.src(paths.js)
        .pipe(concat('public.libs.js'))
        .pipe(gulp.dest('src/shared/libs/js'));
});

//Tarefa para concatenar as libs css public e salvar arquivo em src/shared/libs/css
gulp.task('libs-css', function () {
    gulp.src(paths.css)
        .pipe(concat('public.libs.css'))
        .pipe(gulp.dest('src/shared/libs/css'))
});

gulp.task("public-libs", [
    "libs-css",
    "libs-js",
    "libs-fonts"]
);

// Tarefa padrão quando executado o comando GULP
gulp.task('default', ["public-libs"]);

