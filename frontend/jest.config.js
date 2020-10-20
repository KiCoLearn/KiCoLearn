module.exports = {
    verbose: true,
    transformIgnorePatterns : ['/node_modules/(?!vue-json-excel)'],
    moduleFileExtensions: [
        'js',
        'json',
        'vue'
    ],
    transform: {
        '.*\\.(vue)$': 'vue-jest',
        '^.+\\.js$': '<rootDir>/node_modules/babel-jest'
    },
    collectCoverage: true,
    collectCoverageFrom: [
        '**/*.{js,vue}',
        '!**/node_modules/**'
    ]
};