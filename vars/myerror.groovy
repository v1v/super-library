#!/usr/bin/env groovy

def call(Map params = [:]) {
    def timeout = params.get('timeout') ?: '60'
    error 'Forcing error'
}
