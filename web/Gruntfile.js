module.exports = function (grunt) {
    var APP_DIST_DIR = 'resources/';
    var APP_SRC_DIR = 'app/';
    var APP_TMP_DIR = '.tmp/';
    var JS_DIR_NAME = 'javascripts';
    var CSS_DIR_NAME = 'stylesheets';

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        concat: {
            options: {
                banner: "/* File generated automatically by grunt concat, do not modify it (it is senselessly) */\n"
            },
            js: {
                src: [APP_SRC_DIR + 'app.js',
                    APP_SRC_DIR + 'route.js',
                    APP_SRC_DIR + '**/*.js'
                ],
                dest: APP_DIST_DIR + JS_DIR_NAME + '/full.js'
            },
            styles: {
                src: [
                    APP_TMP_DIR + '**/*.css'
                ],
                dest: APP_DIST_DIR + CSS_DIR_NAME + '/styles.css'
            }
        },
        bower_concat: {
            all: {
                dest: APP_DIST_DIR + JS_DIR_NAME + '/lib.js',
                cssDest: APP_DIST_DIR + CSS_DIR_NAME + '/lib.css',
                callback: function (mainFiles, component) {
                    return mainFiles.map(function (filepath) {
                        // Use minified files if available
                        var min = filepath.replace(/\.js$/, '.min.js');
                        return filepath;//grunt.file.exists(min) ? min : filepath;
                    });
                },
                mainFiles: {
                    bootstrap: 'dist/css/bootstrap.min.css',
                    'bootstrap-duallistbox': [
                        'dist/bootstrap-duallistbox.min.css',
                        'dist/jquery.bootstrap-duallistbox.min.js'
                    ],
                    'angular-ui-select': [
                        'dist/select.css',
                        'dist/select.js'
                    ]
                }
            }
        },
        uglify: {
            build: {
                src: APP_DIST_DIR + JS_DIR_NAME + '/full.js',
                dest: APP_DIST_DIR + JS_DIR_NAME + '/full.min.js'
            }
        },
        watch: {
            scripts: {
                files: [APP_SRC_DIR + '**/*.js'],
                tasks: ['newer:concat:js'],
                options: {
                    spawn: false,
                    livereload: true,
                }
            },
            styles: {
                files: [APP_SRC_DIR + '**/*.less'],
                tasks: ['newer:less', 'newer:concat:styles'],
                options: {
                    spawn: false,
                    livereload: true,
                }
            },
            configFiles: {
                files: ['Gruntfile.js'],
                options: {
                    reload: true
                }
            },
            libs: {
                files: ['bower_components/**'],
                tasks: ['bower_concat']
            },
            templates: {
                files: [APP_SRC_DIR + '**/*.html'],
                tasks: ['ngtemplates'],
                options: {
                    spawn: false,
                    livereload: true,
                }
            }
        },
        ngtemplates: {
            app: {
                cwd: APP_SRC_DIR,

                src: '**/*.html',
                dest: APP_DIST_DIR + JS_DIR_NAME + '/templates.js',
                options: {
                    htmlmin: {
                        //    collapseBooleanAttributes:      true,
                        collapseWhitespace: true,
                        //    removeAttributeQuotes:          true,
                        //    //removeComments:                 true,
                        //    removeEmptyAttributes:          true,
                        //    removeRedundantAttributes:      true,
                        //    removeScriptTypeAttributes:     true,
                        //    removeStyleLinkTypeAttributes:  true
                    },
                    module: 'tdtb'
                }
            }
        },
        ngAnnotate: {
            options: {
                singleQuotes: true,
            },
            app1: {
                files: {full: [APP_DIST_DIR + JS_DIR_NAME + '/full.js']},
                ext: '.annotated',
                extDot: 'last',
            },
        },
        less: {
            components: {
                files: [
                    {
                        expand: true,
                        cwd: APP_SRC_DIR,
                        src: ['**/*.less'],
                        dest: APP_TMP_DIR,
                        ext: '.css'
                    }
                ]
            }

        },
        clean: {
            temp: APP_TMP_DIR
        }
    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-bower-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-angular-templates');
    grunt.loadNpmTasks('grunt-ng-annotate');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-newer');
    grunt.loadNpmTasks('grunt-contrib-htmlmin');

    grunt.registerTask('build', ['clean', 'less', 'concat', 'bower_concat', 'ngtemplates', 'uglify']);
    grunt.registerTask('default', ['build']);
    grunt.registerTask('dev_watch', ['clean', 'less', 'concat', 'bower_concat', 'ngtemplates', 'watch']);

};