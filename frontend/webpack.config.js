const path = require('path');
const CopyPlugin = require('copy-webpack-plugin')

module.exports = {
    mode: 'production',
    entry: {
        cart: './src/cart.tsx',
        store: './src/store.tsx'
    },
    //devtool: 'inline-source-map', // remove to reduce size for production build
    watchOptions: {
        aggregateTimeout: 25000,
        poll: 30000,
        ignored: ['**/node_modules', '/node_modules/', 'node_modules']
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
        ],

    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
    },
    plugins: [
        new CopyPlugin({
            patterns: [
                {
                    from: path.resolve(__dirname, 'dist'),
                    to: '../../target/classes/static/javascript',
                    noErrorOnMissing: true
                }
            ]
        })
    ]
};