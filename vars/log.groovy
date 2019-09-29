#!/usr/bin/env groovy

def call(Map params = [:]) {
    def level = params?.get('level','INFO')
    def text = params?.get('text') ?: error('text is mandatory')

    echo "[${level}] - ${text}"
}
